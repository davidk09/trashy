<template>
  <nav class="navbar" :class="{ show: showNavbar }">
    <div class="navbar-body">
      <div class="navbar-header">
        <router-link class="navbar-brand text-accent" :to="{ name: 'Home' }">
          trashy
        </router-link>
        <button class="navbar-burger" @click="showNavbar = true">
          <font-awesome-icon icon="bars" />
        </button>
      </div>

      <div class="navbar-offcanvas">
        <div class="offcanvas-header">
          <p class="offcanvas-title">Menu</p>
        </div>
        <div class="offcanvas-body">
          <ul class="navbar-links" @click="showNavbar = false">
            <NavLink name="Home" icon="home" />
          </ul>
        </div>
      </div>
      <div class="navbar-backdrop" @click="showNavbar = false"></div>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { ref } from "vue";
import NavLink from "@/components/NavLink.vue";

const showNavbar = ref(false);
</script>
