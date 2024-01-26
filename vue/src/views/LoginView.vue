<template>
  <div id="page-container" class="page-container">
    <!-- Logo and Tagline -->
    <div class="background-container">
      <img src="@/assets/images/Brew_You_Logo.svg" alt="BrewYou Logo" class="logo-image" />

      <p class="tagline">Love beer? Join our community of breweries and beer lovers, and we'll point you in the right
        direction!</p>

    </div>
    <!-- Background container -->
    <div class="background-svg"></div>

    <!-- Sign In container -->

    <div id="login-container" class="login-container">
      <form v-on:submit.prevent="login">
        <h1 class="header-text">Sign In</h1>
        <div role="alert" v-if="invalidCredentials" class="alert-message">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration" class="alert-message">
          Thank you for registering, please sign in.
        </div>

        <div class="form-input-group">
          <label for="username">Username </label>
          <input type="text" id="username" v-model="user.username" required autofocus class="bordered-input" />
        </div>

        <div class="form-input-group">
          <label for="password">Password </label>
          <input type="password" id="password" v-model="user.password" required class="bordered-input" />
        </div>
        <!-- <button type="button" class="indented-button-register bordered-button">Register</button> -->
        <div class="button-wrapper">
        <p> </p>
        <button type="submit" class="indented-button-sign-in bordered-button">Sign in</button>

        <p> </p>
        <router-link v-bind:to="{ name: 'register' }" tag="button"
          class="indented-button-register bordered-button">Register</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";
export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      console.log(this.user);
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
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
  background: url('@/assets/images/Brew_You_Running_Image.svg') no-repeat left top;
  background-size: cover;
  margin-top: 10px;
}

.background-container {
  display: static;
  text-align: center;
  margin-top: 30px;
}

.logo-image {
  width: 500px;
  /* Adjust to make the logo smaller */
  height: auto;
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

/* Login container styles */
#login-container {
  background-repeat: no-repeat;
  background-size: cover;
  /* Adjust this property as needed */
  background-position: top left;
  /* Top-left justified */
  background-color: #B2D3F9;
  /* Fallback background color, Possible to change this to white? */
  border: 4px solid #2328DA;
  border-radius: 10px;
  padding: 1rem;
  width: 20vw;
  max-width: 90%;
  height: auto;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 1);
  /* Adds shadow to Sign In */
  margin-top: 50px;
  /* Spacing from the top */
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  z-index: 2;
}

/* Header text styles */
.header-text {
  text-align: center;
}

/* Alert message styles */
.alert-message {
  padding-left: 5rem;
  color: rgb(11, 196, 73);
  margin-bottom: 5px;
}

.form-input-group {
  margin-bottom: 15px;
  /* Increase spacing between fields */
  display: flex;
  /* Align items in a row */
  align-items: center;
  /* Vertical center alignment */
}

label {
  display: inline-block;
  width: 80px;
  /* Adjust label width */
}

/* Bordered input field styles */
.bordered-input {
  border: 2px solid #0000FF;
  /* 2pt blue border */
  border-radius: 10px;
  /* Rounded corners */
  padding: 0.5rem;
  /* Adjust padding as needed */
  width: calc(100% - 100px);
  /* Adjust input field width */
}

/* Bordered button styles */
.bordered-button {
  border: 2px solid #0000FF;
  /* 2pt blue border */
  border-radius: 10px;
  /* Rounded corners */
  padding: 0.5rem 1rem;
  /* Adjust padding as needed */
  background-color: transparent;
  /* Transparent background, Possible change to white? */
  color: #0000FF;
  /* Blue text color */
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.bordered-button:hover {
  background-color: #21217c;
  /* Blue background on hover */
  color: white;
  /* White text on hover */
}

.button-wrapper {
  text-align: left;
  align-items: left;
}

.indented-button-register {
  text-decoration: none;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 14px;
}</style>
