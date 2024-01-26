
<template>
  <div id="brewery-details-page" class="page-container">
    <!-- Brewery Details and Beer List Components -->
    <brewery-details :brewery="brewery" :current-user="currentUser" />
    <beerList :beers="beers" />

    <!-- Add New Brewery Button (only for admins) -->
    <router-link
      v-if="currentUser.authorities[0].name === 'ROLE_ADMIN'"
      v-bind:to="{ name: 'addBrewery' }"
      class="indented-button-add-brewery bordered-button"
    >
      Add New Brewery
    </router-link>
  </div>
</template>
  
  <script>
  import breweryDetails from '@/components/BreweryDetails.vue';
  import beerList from '../components/BeerList.vue';
  import breweryService from '../services/BreweryService';
  import beerService from '../services/BeerService';
  export default {
    components :{
        breweryDetails,
        beerList
    },
    data() {
      return {
        brewery: null,
        beers: []
      };
    },
    beforeRouteEnter(to, from, next) {
      const breweryId = to.params.id;
      breweryService
        .getBreweryById(breweryId)
        .then((response) => {
          next((vm) => vm.setData(response.data));
        })
        .catch((error) => {
          console.error('Error fetching brewery data:', error);
          next(false);
        });
    },
  
    beforeRouteUpdate(to, from, next) {
    const breweryId = to.params.id;
    this.fetchBeersByBrewery(breweryId);
    next();
  },
    computed: {
        currentUser() {
            return this.$store.state.user;
        }
    },
    methods: {
      setData(brewery) {
        this.brewery = brewery;
        this.fetchBeersByBrewery(brewery.breweryId);
      },
      fetchBeersByBrewery(breweryId) {
      beerService
        .getBeersByBrewery(breweryId)
        .then((response) => {
          this.beers = response.data;
        })
        .catch((error) => {
          console.error('Error fetching beers by brewery:', error);
        });
    },
    },
  };
  </script>
  
  <style scoped>
  /* BreweryDetailsView styles */
  
  #brewery-details-page {
    display: relative;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    background: url("@/assets/images/Brew_You_Running_Image.svg") no-repeat left top;
    background-size: cover;
    margin-top: 10px;
  }
  
  .background-container {
    display: static;
    text-align: center;
    margin-top: 30px;
  }
  
  .logo-image {
    height: 200px;
    margin-top: 0;
  }
  
  .tagline {
    display: absolute;
    top: 20px;
    text-align: center;
    margin-bottom: 50px;
    color: rgb(62, 134, 198);
    font-weight: bold;
  }
  
  .home-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    padding: 20px;
  }
  
  .bordered-button {
    border: 4px solid #0000ff;
    border-radius: 10px;
    padding: 0.5rem 1rem;
    background-color: #ff9494;
    color: #0000ff;
    font-weight: bold;
    cursor: pointer;
    transition: transform 0.3s ease;
  }
  
  .bordered-button:hover {
    border: 4px solid #0000ff;
    background-color: #0000ff;
    color: white;
    transform: scale(1.05);
  }
  
  .indented-button-add-brewery {
    text-decoration: none;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 14px;
    margin-top: 20px;
  }
  
  /* Add your specific BreweryDetailsView component styles below */
  
  </style>
  