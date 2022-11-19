<template>
  <div class="container">
    <div
      style="display: flex; align-items: start; justify-content: space-between"
    >
      <div class="flex" style="align-items: center">
        <img
          alt="Profile Picture"
          style="
            width: 2.5rem;
            height: 2.5rem;
            border-radius: 50%;
            margin-right: 0.5rem;
          "
          :src="require('@/assets/images/default_pfp.jpeg')"
        />
        <h2 class="text-lesser">PanchoK50</h2>
      </div>
      <div>
        <h2 style="color: #4ca497; font-size: 1.2rem">
          500 <font-awesome-icon icon="recycle" />
        </h2>
        <h2 style="color: #8552b4; font-size: 1.2rem">
          500 <font-awesome-icon icon="trash" />
        </h2>
      </div>
    </div>
    <button
      class="button"
      style="width: 100%; margin-top: 1rem"
      @click="cameraScreen?.open()"
    >
      <font-awesome-icon icon="camera" /> Take Trashy
    </button>
    <h2 style="margin-top: 2.5rem">Latest activity:</h2>
    <div v-for="i in 4" :key="i" style="margin-top: 0.5rem" class="card">
      <h3>New Trashy</h3>
      <p class="text-lesser">Dakra took a trashy at one of your cans</p>
      <p style="color: #4ca497">+20 <font-awesome-icon icon="recycle" /></p>
    </div>
  </div>
  <camera-screen ref="cameraScreen" />
</template>

<script setup lang="ts">
import CameraScreen from "@/components/CameraScreen.vue";
import { ref } from "vue";

const cameraScreen = ref<typeof CameraScreen>();
</script>
