<template>
  <div class="page-container">
    <!-- Logo -->
    <img
      src="@/assets/images/Brew_You_Logo.svg"
      alt="BrewYou Logo"
      class="logo-image"
    />

    <!-- Registration Form -->
    <div id="register" class="register-container">
      <form v-on:submit.prevent="register">
        <h1 class="header-text">Create Account</h1>
        <div role="alert" v-if="registrationErrors" class="alert-message">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input
            type="text"
            id="username"
            v-model="user.username"
            required
            autofocus
            class="bordered-input"
          />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            v-model="user.password"
            required
            class="bordered-input"
          />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password</label>
          <input
            type="password"
            id="confirmPassword"
            v-model="user.confirmPassword"
            required
            class="bordered-input"
          />
        </div>
        <div class="button-wrapper">
          <p></p>
          <button type="submit" class="indented-button-sign-in bordered-button">
            Create Account
          </button>
          <p></p>
          <router-link
            v-bind:to="{ name: 'login' }"
            tag="button"
            class="indented-button-register bordered-button"
            >Sign In</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
/* Page container styles */

.page-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  text-align: center;
}

.logo-image {
  width: 500px;
  /* Adjust to make the logo smaller */
  height: auto;
  /* Maintain aspect ratio */
  margin-top: 0;
}

.register-container {
  background-repeat: no-repeat;
  background-size: cover;
  background-position: top left;
  background-color: #b2d3f9;
  /* Consistent background color */
  border: 4px solid #2328da;
  /* Border styling */
  border-radius: 10px;
  /* Rounded corners */
  padding: 1rem;
  /* Padding around the form */
  width: 20vw;
  max-width: 90%;
  height: auto;
  /* Width of the form */
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 1);
  /* Box shadow for depth */
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
  color: rgb(196, 11, 11);
  margin-bottom: 5px;
  /* Spacing after the alert message */
}

.form-input-group {
  margin-bottom: 15px;
  /* Spacing between input groups */
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
  border: 2px solid #0000ff;
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
  border: 2px solid #0000ff;
  /* Blue border */
  border-radius: 10px;
  /* Rounded corners */
  padding: 0.5rem 1rem;
  /* Padding inside the button */
  background-color: #5a92e1;
  /* Transparent background */
  color: black;
  /* Blue text color */
  cursor: pointer;
  /* Cursor changes to pointer on hover */
  transition: background-color 0.3s ease, color 0.3s ease;
  /* Smooth transition for hover effect */
}

.bordered-button:hover {
  background-color: #21217c;
  /* Blue background on hover */
  color: white;
  /* White text on hover */
}

.button-wrapper {
  text-align: left;
  alight-items: left;
}

.indented-button-register,
.indented-button-sign-in {
  text-decoration: none;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 14px;
}
</style>
