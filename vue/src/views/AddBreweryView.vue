<template>
  <div class="page-container">
    <addBrewery />
  </div>
</template>
<script>
import addBrewery from "../components/AddBrewery.vue";

export default {
  components: {
    addBrewery,
  },
};
</script>

<style scoped>
.page-container {
  display: relative;
  min-height: 100vh;
  /* Minimum height of the viewport */
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  background: url("@/assets/images/Brew_You_Running_Image.svg") no-repeat left
    top;
  background-size: cover;
  margin-top: 10px;
}
.background-container {
  display: static;
  text-align: center;
  margin-top: 30px;
}
</style>
