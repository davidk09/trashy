<template>
  <div class="wrapper-outer">
    <div class="wrapper-inner">
      <navbar-component />
      <div><router-view /></div>
    </div>
  </div>

  <footer class="footer-bottom">
    <p class="footer-item">Impressum</p>
    <p class="footer-item">Datenschutz</p>
  </footer>
</template>

<script setup lang="ts">
import NavbarComponent from "@/components/NavbarComponent.vue";
</script>

<style lang="scss">
@import "@/assets/cibumArs.css";
@import "@/assets/style.scss";
</style>
