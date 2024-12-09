<template>
  <Header></Header>
  <!-- Graph container -->
  <div class="container" id="graph">
    <p>Graph</p>
    <canvas id="canvas_graph_grid" width="490" height="490"></canvas>
    <canvas id="canvas_graph_figures" width="490" height="490"></canvas>
    <canvas id="canvas_graph_points" width="490" height="490"></canvas>
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
          <button class="button clear_button">Delete Points</button>
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
      <tr v-for="(point, index) in points" :key="index" style="display: flex; justify-content: space-around;">
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

import {defineComponent, onMounted} from "vue";
import Header from "@/components/HeaderComponent.vue";
import {initializeGraph, drawingFigure, deleteFigures} from "@/assets/js/graph.js";
import axios from 'axios';

export default defineComponent({
  components: {Header},
  setup() {
    onMounted(() => {
      initializeGraph();
    });
  },
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
        deleteFigures();
        drawingFigure(parseFloat(r));
      }
    }
  },
  methods: {
    async addPoint() {
      console.log("add point started working")
      try {
        const url = 'http://localhost:8080/point';
        const response = await axios.post(url, {
          x: parseFloat(this.x),
          y: parseFloat(this.y),
          r: parseFloat(this.r)
        });
        this.points.push(response.data.point);
        console.log(this.points);
      } catch (error) {
        if (error.response && error.response.data) {
          alert(error.response.data.message);
        } else {
          alert("An error occurred while adding the point.");
        }
        console.log(error);
      }
    }
  }
});


</script>

<style>
.clear_button {
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