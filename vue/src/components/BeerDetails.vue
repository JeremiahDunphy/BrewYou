<template>
  <div class="beer-card" v-if="beer">
    <div class="beer-header">
      <h3>{{ beer.name }}</h3>
    </div>
    <div class="beer-content">
      <div class="beer-image-container">
        <img :src="beer.imagePath" alt="Beer Image" class="beer-image" />
      </div>
      <div class="beer-details">
        <p>{{ beer.description }}</p>
        <p>ABV: {{ beer.abv }}%</p>
        <p>Type: {{ beer.beerType }}</p>
      </div>
    </div>
  </div>
  <div v-else>Nothing to show</div>
</template>

<script>
export default {
  props: {
    beer: Object,
  },
};
</script>

<style scoped>
/* Add your styles for the beer card here */
.beer-card {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  background-color: #5a92e1;
  border: 4px solid #2328DA;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.6);
}

.beer-header {
  color: white;
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 10px;
}

.beer-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.beer-image-container {
  margin-right: 20px;
}

.beer-image {
  max-width: 200px;
  height: 200px;
  border-radius: 8px;
  border: 2px solid white;
}

.beer-details {
  flex: 1;
  text-align: left;
}

.beer-details p {
  color: white;
  margin: 0;
}

/* Add more styles as needed */
</style>
