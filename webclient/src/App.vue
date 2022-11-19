<template>
  <div class="wrapper-outer">
    <div class="wrapper-inner">
      <navbar-component />
      <router-view />
    </div>
  </div>

  <footer class="footer-bottom">
    <p class="footer-item" style="text-decoration: none">&copy;2022 trashy</p>
  </footer>
</template>

<script setup lang="ts">
import NavbarComponent from "@/components/NavbarComponent.vue";
</script>

<style lang="scss">
@import "@/assets/cibumArs.css";
@import "@/assets/style.scss";
</style>
