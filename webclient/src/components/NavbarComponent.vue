<template>
  <nav class="navbar" :class="{ show: showNavbar }">
    <div class="navbar-body">
      <div
        class="navbar-header"
        style="width: 100%; justify-content: space-between"
      >
        <router-link
          class="navbar-brand"
          :to="{ name: 'Home' }"
          style="height: 3rem; margin: 0"
        >
          Hello!
        </router-link>
        <button
          class="navbar-burger"
          @click="showNavbar = true"
          style="margin: 0; color: #cacbcb"
        >
          <font-awesome-icon icon="bars" />
        </button>
      </div>

      <div class="navbar-offcanvas">
        <div class="offcanvas-header">
          <p class="offcanvas-title">Men&uuml;</p>
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
