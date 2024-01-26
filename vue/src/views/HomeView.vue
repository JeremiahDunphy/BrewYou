<template>
  <div id="page-container" class="page-container">
    <!-- Logo and Tagline -->
    <div class="background-container">
      <img
        src="@/assets/images/Secrets_Image_no_border.svg"
        alt="BrewYou Logo"
        class="logo-image"
      />
      <p class="tagline">
        Love beer? Join our community of breweries and beer lovers, and we'll
        point you in the right direction!
      </p>
    </div>
    <!-- Background container -->
    <div class="background-svg"></div>
    <!-- Home content -->
    <div class="home-content">
      <BreweryList />
      <router-link
        v-if="currentUser.authorities[0].name === 'ROLE_ADMIN'"
        v-bind:to="{ name: 'addBrewery' }"
        class="indented-button-add-brewery bordered-button"
        >Add New Brewery</router-link
      >
    </div>
  </div>
</template>
<script>
import BreweryList from "@/components/BreweryListHome.vue";
export default {
  components: {
    BreweryList,
  },
  computed: {
    currentUser() {
      return this.$store.state.user;
    },
  },
};
</script>
<style scoped>
/* Page container styles */
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
.logo-image {
  /* Adjust to make the logo smaller */
  height: 200px;
  /* Maintain aspect ratio */
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
/* Home content styles */
.home-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 20px;
}
/* Bordered button styles */
.bordered-button {
  border: 4px solid #0000ff;
  /* 2pt blue border */
  border-radius: 10px;
  /* Rounded corners */
  padding: 0.5rem 1rem;
  /* Adjust padding as needed */
  background-color: #ff9494;
  /* Transparent background, Possible change to white? */
  color: #0000ff;
  font-weight: bold;
  /* Blue text color */
  cursor: pointer;
  transition: transform 0.3s ease;
}
.bordered-button:hover {
  border: 4px solid #0000ff;
  background-color: #0000ff;
  /* Blue background on hover */
  color: white;
  /* White text on hover */

  transform: scale(1.05); /* Increase size on hover */
}

.indented-button-add-brewery {
  text-decoration: none;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 14px;
  margin-top: 20px;
}
</style>
