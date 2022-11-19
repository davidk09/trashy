<template>
  <li class="navbar-link" :class="$route.name === name ? 'active' : ''">
    <router-link class="link-text" :to="{ name: name }">
      <font-awesome-icon class="link-icon" :icon="icon" />{{ name }}
    </router-link>
  </li>
</template>

<script setup lang="ts">
defineProps<{
  name: string;
  icon: string;
}>();
</script>
