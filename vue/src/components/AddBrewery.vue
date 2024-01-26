<template>
  <div class="brewery-form">
    <h2>Add New Brewery</h2>
    <form @submit.prevent="addBrewery" class="brewery-form-layout">
      <!-- Name -->
      <label for="name">Name:</label>
      <input
        type="text"
        id="name"
        v-model="brewery.name"
        required
        maxlength="30"
      />

      <!-- Founded Date -->
      <label for="foundedDate">Founded Date:</label>
      <input
        type="date"
        id="foundedDate"
        v-model="brewery.foundedDate"
        required
      />

      <!-- Contact Phone -->
      <label for="contactPhone">Contact Phone:</label>
      <input
        type="tel"
        id="contactPhone"
        v-model="brewery.contactPhone"
        required
        maxlength="15"
      />

      <!-- Email -->
      <label for="email">Email:</label>
      <input
        type="email"
        id="email"
        v-model="brewery.email"
        required
        maxlength="30"
      />

      <!-- Street Address -->
      <label for="streetAddress">Street Address:</label>
      <input
        type="text"
        id="streetAddress"
        v-model="brewery.streetAddress"
        required
        maxlength="30"
      />

      <!-- City -->
      <label for="city">City:</label>
      <input
        type="text"
        id="city"
        v-model="brewery.city"
        required
        maxlength="30"
      />

      <!-- State -->
      <label for="state">State:</label>
      <input
        type="text"
        id="state"
        v-model="brewery.state"
        required
        maxlength="30"
      />

      <!-- Zip Code -->
      <label for="zipCode">Zip Code:</label>
      <input
        type="number"
        id="zipCode"
        v-model="brewery.zipCode"
        required
        maxlength="10"
      />

      <!-- Days of Operation -->
      <label for="daysOfOperation">Days of Operation:</label>
      <input
        type="text"
        id="daysOfOperation"
        v-model="brewery.daysOfOperation"
        required
      />

      <!-- Hours of Operation -->
      <label for="hoursOfOperation">Hours of Operation:</label>
      <input
        type="text"
        id="hoursOfOperation"
        v-model="brewery.hoursOfOperation"
        required
      />

      <!-- Image Path -->
      <label for="imagePath">Image Path:</label>
      <input type="text" id="imagePath" v-model="brewery.imagePath" required />

      <!-- Submit Button -->
      <button type="submit">Add Brewery</button>
    </form>
  </div>
</template>

<script>
import breweryService from "../services/BreweryService";

export default {
  data() {
    return {
      brewery: {
        name: "",
        foundedDate: null,
        contactPhone: "",
        email: "",
        streetAddress: "",
        city: "",
        state: "",
        zipCode: null,
        daysOfOperation: "",
        hoursOfOperation: "",
        imagePath: "",
      },
    };
  },
  methods: {
    addBrewery() {
      breweryService
        .addBrewery(this.brewery)
        .then((response) => {
          console.log(response.status);
          console.log(this.brewery);
          this.$router.push({ name: "home" });
        })
        .catch((error) => {
          console.error("Error adding brewery:", error);
        });
    },
  },
};
</script>

<style scoped>
.brewery-form {
  max-width: 500px;
  margin: auto;
  background-color: #f2f2f2;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

.brewery-form-layout {
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 10px;
}

label {
  text-align: right;
  align-self: center;
}

input,
button {
  padding: 8px;
  border-radius: 5px;
  border: 2px solid rgb(62, 134, 198);
}

button {
  grid-column: 1 / span 2;
  background-color: rgb(62, 134, 198);
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: rgb(62, 134, 198);
}

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
  background-color: #b2d3f9;
  /* Fallback background color, Possible to change this to white? */

  background-size: cover;
  margin-top: 10px;
}

.brewery-form {
  border: 4px solid #2328da;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 1);
}

.background-container {
  display: static;
  text-align: center;
  margin-top: 30px;
}
</style>
