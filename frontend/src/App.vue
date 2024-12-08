<template>
    <router-view />
</template>

<script>
</script>

<style>
/* import Roboto */
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400&display=swap');

* {
  padding: 0;
  margin: 0;
  border: 0px;
  outline: 0;
  box-sizing: border-box;
}

body {
  background-image: url("assets/background.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  color: #002636;
  font-size: 24px;
  font-weight: 300;
  font-style: normal;
  font-family: "Roboto", "sans-serif";
  width: 100%;
  overflow: auto;
}

button {
  padding: 5px;
  font-weight: 500;
  font-style: normal;
  font-family: "Roboto", sans-serif;
}

.container {
  border-radius: 30px;
  box-shadow: 10px 10px 20px 0px rgba(0, 0, 0, 0.25);
}

.input {
  display: block;
  margin-bottom: 10px;
  padding: 10px;
  font-weight: 300;
  font-style: normal;
  font-family: "Roboto", sans-serif;
  color: #002636;
  font-size: 14px;
  background: rgba(0, 38, 54, 0);
  width: 260px;
  height: 26px;
  border-radius: 30px;
  border: 1px solid #002636;
}

.submit_button {
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

</style>
