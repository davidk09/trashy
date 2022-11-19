<template>
  <div class="card" :class="buy ? 'buy' : 'sell'" style="margin-top: 0.5rem">
    <h3>
      {{ buy ? "Bought" : "Sold" }} Trashcans
      <span class="text-primary">({{ date }})</span>
    </h3>
    <p>
      <span :class="buy ? 'not-profit' : 'money'"
        >{{ buy ? "-" : "+" }}{{ price * qty }}
        <font-awesome-icon icon="recycle"
      /></span>
      |
      <span :class="buy ? 'money' : 'not-profit'"
        >{{ buy ? "+" : "-" }}{{ qty }} <font-awesome-icon icon="trash"
      /></span>
    </p>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  buy: boolean;
  qty: number;
  price: number;
  date: string;
}>();
</script>
