<template>
  <div id="capstone-app">
    <div id="nav" class="header">
      <!-- Clickable logo redirecting to home -->
      <router-link to="/">
        <img
          src="src/assets/images/Brew_You_Logo_Version_3.svg"
          width="200"
          height="200"
          alt="Logo"
        />
      </router-link>

      <!-- Removed Home link -->
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;&nbsp; -->

      <!-- Login/Logout link -->
      <router-link
        :to="{ name: $store.state.token ? 'logout' : 'login' }"
        class="button-style"
      >
        {{ $store.state.token ? "Log Out" : "Log In" }}
      </router-link>
    </div>
    <router-view />
  </div>
</template>
<style>
/* Global font style */
/* Global font style */
body {
  font-family: "Futura", sans-serif;
}

#nav {
  display: flex;
  justify-content: space-between; /* Aligns children to opposite ends */
  background-color: #5a92e1;
  color: black;
  padding: 10px;
  height: 100px;
  align-items: center;
  border-bottom: 4px solid #2328da;
  box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.5);
}

#nav a {
  text-decoration: none;
  margin: 0 15px;
  padding: 5px 10px;
  font-weight: 600;
  transition: transform 0.3s ease, color 0.3s ease;
}

#nav a:hover {
  text-decoration: none;
  color: #2328da;
  transform: scale(1.1);
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
}

.button-style {
  background-color: #ff9494; /* Button background color */
  color: #0000ff; /* Text color */
  padding: 8px 15px; /* Padding around the text */
  border: 3px solid #0000ff; /* No border */
  border-radius: 5px; /* Rounded corners */
  text-align: center; /* Center the text */
  text-decoration: none; /* Remove underline */
  transition: background-color 0.3s, transform 0.3s; /* Smooth transition for hover effects */
  font-weight: 600; /* Bold text */
  cursor: pointer; /* Change cursor to pointer */
  display: inline-block; /* Allows padding and width */
}

/* Increased specificity for button hover state */
#nav .button-style:hover,
#nav .button-style:active {
  background-color: #0000ff; /* Dark blue background on hover/active */
  color: white; /* Change text color to white */
  transform: scale(1.05); /* Slightly scale the button */
}

</style>
