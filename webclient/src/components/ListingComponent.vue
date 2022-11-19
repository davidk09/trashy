<template>
  <div class="card" :class="buy ? 'buy' : 'sell'" style="margin-top: 0.5rem">
    <h3>{{ buy ? "Buy" : "Sell" }} Trashcans</h3>
    <p>{{ buy ? "Want" : "Have" }} {{ qty }}</p>
    <p>
      <span style="color: #4ca497"
        >{{ price }} <font-awesome-icon icon="recycle"
      /></span>
      each
    </p>
    <div class="cancel-button" @click="$emit('cancel')">
      <font-awesome-icon icon="square-xmark" />
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  buy: boolean;
  qty: number;
  price: number;
}>();
defineEmits(["cancel"]);
</script>
