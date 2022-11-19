<template>
  <div class="mb-6">
    <h1 style="margin-bottom: 0.1rem">
      <font-awesome-icon
        :icon="icon"
        style="margin-right: 1rem"
        @click="$router.push({ name: 'Home' })"
      />
      {{ title }}
    </h1>
    <h3 class="text-lesser" style="margin-top: 0.1rem">
      {{ subtitle }}
    </h3>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  title: string;
  subtitle: string;
  icon: string;
}>();
</script>
