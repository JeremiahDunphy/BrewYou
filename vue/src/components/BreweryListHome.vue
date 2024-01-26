<template>
  <div class="card-container">
    <div
      v-for="brewery in breweries"
      :key="brewery.breweryId"
      v-on:click="viewBreweryDetails(brewery.breweryId)"
    >
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">{{ brewery.name }}</h5>
          <p class="card-text">
            {{ brewery.streetAddress }}, {{ brewery.city }},
            {{ brewery.state }} {{ brewery.zip_code }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import breweryService from "../services/BreweryService";

export default {
  data() {
    return {};
  },
  computed: {
    // Map the breweries data from the store to a computed property
    breweries() {
      return this.$store.state.breweries;
    },
  },
  created() {
    this.displayBreweries();
  },
  methods: {
    displayBreweries() {
      breweryService.getBreweries().then((response) => {
        console.log(response.data); // Log the response data
        this.$store.commit("SET_BREWERIES", response.data);
      });
    },
    viewBreweryDetails(breweryId) {
      this.$router.push({ name: "brewery", params: { id: breweryId } });
    },
  },
};
</script>

<style scoped>
.card-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center; /* Center the cards horizontally */
}

.card {
  width: 300px;
  /* Adjust card width as needed */
  border: 3px solid #0000ff; /* Add blue border */
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
  margin: 10px; /* Add margin to cards */
  transition: transform 0.2s, box-shadow 0.2s, background-color 0.2s; /* Add transition for hover effect */
  background-color: white; /* Initial background color */
}

.card:hover {
  transform: scale(1.05); /* Increase size on hover */
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Add shadow on hover */
  background-color: #b2d3f9; /* Change background color to baby blue on hover */
  border: 4px solid #0000ff;
}

.card-body {
  padding: 16px;
}

.card-title {
  font-size: 1.25rem;
  margin-bottom: 8px;
}

.card-text {
  font-size: 1rem;
  color: #555;
}
</style>
