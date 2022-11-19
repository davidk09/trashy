<template>
  <div id="map" style="height: calc(100vh - 6.5rem)"></div>
</template>

<script setup lang="ts">
import mapboxgl, { GeolocateControl } from "mapbox-gl";
import { onMounted } from "vue";

mapboxgl.accessToken =
  "pk.eyJ1IjoiMmtkb2JieSIsImEiOiJja3c4MXNjaGkwYm05MnZsZjJsOHQyeXJjIn0.-e14iF3xgBYh4Ie3JZoO_Q";
onMounted(() => {
  const map = new mapboxgl.Map({
    container: "map",
    style: "mapbox://styles/2kdobby/clanopc8e002414o6m8vwmojc",
    center: [11.57, 48.13],
    zoom: 12,
    interactive: false,
  });
  map.addControl(
    new GeolocateControl({
      showAccuracyCircle: true,
    })
  );
});
</script>

<style scoped>
@import "mapbox-gl/dist/mapbox-gl.css";
</style>
