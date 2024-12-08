<template>
  <div class="container" id="header">
    <p class="group">P3206</p>
    <div class="name">
      <p>Medvedev Vladislav</p>
      <p>Aleksandrovich</p>
    </div>
    <div class="info">
      <p>Lab Work 4</p>
      <p>№var 115244</p>
    </div>
  </div>
</template>

<script>
</script>

<style scoped>

.group {
  position: absolute;
  margin-top: 27px;
  font-size: 40px;
  left: 11%;
}

.name {
  position: absolute;
  margin-top: 27px;
  left: 20%;
  font-size: 20px;
}

.info p:nth-child(1) {
  position: absolute;
  margin-top: 36px;
  font-size: 24px;
  left: 50%;
}

.info p:nth-child(2) {
  position: absolute;
  margin-top: 36px;
  font-size: 24px;
  left: 80%;
}

#header {
  display: flex;
  background: rgba(255, 255, 255, 0.45);
  margin-top: 40px;
  margin-left: 40px;
  margin-right: 40px;
  width: auto;
  height: 100px;
  position: relative;
}

</style>