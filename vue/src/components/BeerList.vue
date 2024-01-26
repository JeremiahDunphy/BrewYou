<template>
  <div class="beer-list">
    <button class="toggle-button" @click="toggleBeerList">
      {{ showBeerList ? 'Hide List of Beers' : 'Show List of Beers' }}
    </button>
    <div class="beer-cards" v-show="showBeerList">
      <div v-for="beer in beers" :key="beer.id" class="beer-card">
        <img :src="beer.imagePath" class="beer-image" />
        <!-- <img :src="import(`@assets/images/Beer_Can.svg`)" alt="Beer Image" class="beer-image" /> -->
        <div class="beer-details">
          <h3>{{ beer.name }}</h3>
          <p>ABV: {{ beer.abv }}%</p>
          <!-- Add more beer details as needed -->
          <router-link :to="{ name: 'beer', params: { id: beer.beerId } }"
            ><button class="view-details-button">
              View Details
            </button></router-link
          >
        </div>
     
      </div>
    </div>
    <!-- Handle case when there are no beers -->
    <div v-if="!beers.length && showBeerList">
      <p>No beers available for this brewery.</p>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    beers: Array,
  },
  data() {
    return {
      showBeerList: false, // Initial state is true to show the list
    };
  },
  methods: {
    toggleBeerList() {
      this.showBeerList = !this.showBeerList;
    },
  },
};
</script>
<style scoped>
/* Add your styles for the beer list here */
.beer-list {
  /* Customize as needed */
  margin: 20px;
}
.toggle-button {
  border: 2px solid #0000ff;
  border-radius: 10px;
  padding: 0.5rem 1rem;
  background-color: #5a92e1;
  font-weight: 700;
  color:black;;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
  margin-bottom: 15px;
}

.toggle-button:hover {
  background-color: #21217c;
  color: white;
}

/* Beer Cards */
.beer-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  
}

.beer-card {
  /* Add styles for each beer card */
  margin: 10px;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 200px; /* Adjust the card width as needed */
  background-color: #5a92e1;
  border: 4px solid #2328DA;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 1);
  display: flex;
  align-items: center;
}

.beer-image {
  /* Add styles for the beer image */
  max-width: 100px;
  height: 100px;
  margin-right: 10px;
  border-radius: 6px;
}

.beer-details {
  /* Add styles for the beer details */
  margin-top: 10px;
  flex: 1; 
}

.view-details-button {
  border: 2px solid #0000ff;
  /* 2pt blue border */
  border-radius: 10px;
  /* Rounded corners */
  padding: 0.5rem 1rem;
  /* Adjust padding as needed */
  background-color: transparent;
  /* Transparent background, Possible change to white? */
  color: #0000ff;
  /* Blue text color */
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
}
.view-details-button:hover {
  background-color: #21217c;
  /* Blue background on hover */
  color: white;
  /* White text on hover */

}
</style>
