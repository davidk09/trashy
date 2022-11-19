<template>
  <div class="container">
    <div style="display: flex; justify-content: space-between">
      <div class="flex" style="align-items: center">
        <div
          style="
            width: 2.5rem;
            height: 2.5rem;
            background-color: #245c88;
            border-radius: 50%;
            margin-right: 0.5rem;
          "
        ></div>
        <h2 class="text-lesser">PanchoK50</h2>
      </div>
      <h2 style="color: #4ca497">500 <font-awesome-icon icon="recycle" /></h2>
    </div>
    <hr style="color: var(--primary-color); margin-top: 2rem" />
    <h2 style="margin-top: 2rem">Latest activity:</h2>
    <div v-for="i in 5" :key="i" style="margin-top: 0.5rem" class="card">
      <h3>New Trashy</h3>
      <p class="text-lesser">Dakra took a trashy at one of your cans</p>
      <p style="color: #4ca497">+20 TP</p>
    </div>
    <router-link
      style="display: block; text-align: center; margin-top: 0.5rem"
      :to="{ name: 'Home' }"
      class="text-primary"
      >show more...</router-link
    >
  </div>
</template>

<script setup lang="ts"></script>
<script>
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
export default {
  components: { FontAwesomeIcon },
};
</script>
