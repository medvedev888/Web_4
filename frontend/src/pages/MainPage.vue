<template>
  <Header></Header>
  <!-- Graph container -->
  <div class="container" id="graph">
    <p>Graph</p>
    <canvas id="canvas_graph_grid" width="490" height="490"></canvas>
    <canvas id="canvas_graph_figures" width="490" height="490"></canvas>
    <canvas id="canvas_graph_points" width="490" height="490" @click="handleClick"></canvas>
  </div>

  <!-- Enter parameters container -->
  <div class="container" id="parameters">
    <p>Enter parameters</p>
    <p style="margin-bottom: 10px">(Every parameter should be filled!):</p>

    <div>
      <form @submit.prevent="addPoint">
        <div style="display: flex; flex-direction: column;">
          <label for="x">Enter X:</label>
          <input id="x" class="input" type="text" required v-model="x"/>
        </div>
        <div style="display: flex; flex-direction: column;">
          <label for="y">Enter Y:</label>
          <input id="y" class="input" type="text" required v-model="y"/>
        </div>
        <div style="display: flex; flex-direction: column;">
          <label for="r">Enter R:</label>
          <input id="r" class="input" type="text" required v-model="r"/>
        </div>
        <div style="display: flex; flex-direction: column;">
          <button class="button submit_button" type="submit">Submit</button>
          <button class="button clear_button" type="button" @click="deleteAllPoints">Delete Points</button>
          <button class="button logout_button" type="button"  style="margin-top: 30px;" @click="logout">Logout</button>
        </div>
      </form>
    </div>

  </div>

  <!-- Result Table Container -->
  <div class="container" id="table">
    <table style="width: 100%;">
      <thead>
      <tr style="display: flex; justify-content: space-around;">
        <td>X</td>
        <td>Y</td>
        <td>R</td>
        <td>Result</td>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(point, index) in points.slice().reverse()" :key="index" style="display: flex; justify-content: space-around;">
        <td>{{ point.x }}</td>
        <td>{{ point.y }}</td>
        <td>{{ point.r }}</td>
        <td>{{ point.result }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

import {defineComponent} from "vue";
import Header from "@/components/HeaderComponent.vue";
import {initializeGraph, drawingFigure, deleteFigures, drawPoint, redrawPoints, getCoordinatesWhenMouseMove} from "@/assets/js/graph.js";
import axios from 'axios';

export default defineComponent({
  components: {Header},
  data() {
    return {
      x: 0.0,
      y: 0.0,
      r: 0.0,
      points: []
    };
  },
  watch: {
    r(r) {
      if (r) {
        this.updatePointsWithNewR();
        deleteFigures();
        drawingFigure(parseFloat(r));
        redrawPoints(this.points);
      }
    },
    points(points) {
      if(points) {
        deleteFigures();
        drawingFigure(parseFloat(this.r));
        redrawPoints(points);
      }
    }
  },
  methods: {
    async addPoint() {
      console.log("addPoint");
      console.log(this.x, this.y, this.r);
      try {
        const url = 'http://localhost:8080/point';
        const response = await axios.post(url, {
          x: parseFloat(this.x),
          y: parseFloat(this.y),
          r: parseFloat(this.r)
        });
        let point = response.data.point;
        this.points.push(point);
        drawPoint(point.x, point.y, point.result);
      } catch (error) {
        if (error.response && error.response.data) {
          alert(error.response.data.message);
        } else {
          alert("An error occurred while adding the point.");
        }
        console.log(error);
      }
    },
    async getListOfPoints() {
      try {
        const url = 'http://localhost:8080/points';
        const response = await axios.get(url);
        this.points = response.data.listOfPoints;
      } catch (error) {
        console.error("Error when getting list of points", error);
      }
    },
    async deleteAllPoints() {
      try {
        const url = 'http://localhost:8080/points';
        await axios.delete(url);
        await this.getListOfPoints();
      } catch (error) {
        console.error("Error when deleting points", error);
      }
    },
    async updatePointsWithNewR() {
      try {
        const url = 'http://localhost:8080/points/r';
        const response = await axios.patch(url, {
          r : parseFloat(this.r)
        });
        console.log(response.data);
        await this.getListOfPoints();
      } catch (error) {
        console.error("Error when updating points with new R", error);
      }
    },
    handleClick(event) {
      console.log("handle click");
      const { xCoordinate: x, yCoordinate: y } = getCoordinatesWhenMouseMove(event);

      console.log(x, y);
      this.x = x;
      this.y = y;

      this.$nextTick(() => {
        this.addPoint();
      });
    },
    logout() {
      console.log("logout");
      localStorage.removeItem("authToken");
      this.$router.push("/");
    }
  },
  mounted() {
    initializeGraph();
    this.getListOfPoints();
  }
});


</script>

<style>
.clear_button,
.logout_button {
  margin-top: 5px;
  position: relative;
  color: #002636;
  font-size: 14px;
  border-radius: 30px;
  background: rgba(0, 38, 54, 0.1);
  border: 1px solid #002636;
  width: 260px;
  height: auto;
}

#graph,
#parameters,
#table {
  float: left;
  margin-left: 20px;
  margin-top: 20px;
}

#graph {
  padding: 15px;
  background: rgba(255, 255, 255, 0.45);
  display: inline-block;
  height: fit-content;
  width: fit-content;
  position: relative;
  margin-left: 50px;
}

#canvas_graph_figures,
#canvas_graph_points {
  position: absolute;
  left: 15px;
}

#parameters {
  font-size: 18px;
  padding: 15px;
  display: inline-block;
  background: rgba(255, 255, 255, 0.45);
  width: auto;
  height: auto;
  position: relative;
}

#parameters div {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

#table {
  width: 470px;
  font-size: 18px;
  padding: 15px;
  display: inline-block;
  background: rgba(255, 255, 255, 0.45);
  position: relative;
}

#table td {
  padding-right: 20px;
}

</style>