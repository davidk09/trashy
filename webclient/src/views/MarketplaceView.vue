<template>
  <div class="container">
    <page-title
      title="Marketplace"
      subtitle="buy and sell trashcans"
      icon="arrow-left"
    />
    <div class="gapped-group">
      <button class="button">Buy Trashcans</button>
      <button class="button">Sell Trashcans</button>
    </div>
    <h2 style="margin-top: 1rem">Your listings:</h2>
    <div>
      <div class="card buy">
        <h3>Buy Trashcans</h3>
        <p>Want 1000</p>
        <p>
          <span style="color: #4ca497"
            >10 <font-awesome-icon icon="recycle"
          /></span>
          each
        </p>
      </div>
      <div class="card sell" style="margin-top: 0.5rem">
        <h3>Sell Trashcans</h3>
        <p>Selling 100</p>
        <p>
          <span style="color: #4ca497"
            >100 <font-awesome-icon icon="recycle"
          /></span>
          each
        </p>
      </div>
    </div>
    <h2 style="margin-top: 1rem">Past transactions:</h2>
    <div>
      <div class="card sell">
        <h3>Sold Trashcans</h3>
        <p>
          <span class="money">+100 <font-awesome-icon icon="recycle" /></span> |
          <span class="not-profit"
            >-1000 <font-awesome-icon icon="trash"
          /></span>
        </p>
      </div>
      <div class="card buy" style="margin-top: 0.5rem">
        <h3>Bought Trashcans</h3>
        <p>
          <span class="not-profit"
            >-1000 <font-awesome-icon icon="recycle"
          /></span>
          |
          <span class="money">+100 <font-awesome-icon icon="trash" /></span>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import PageTitle from "@/components/PageTitle";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
</script>

<style scoped>
.card.sell > h3 {
  color: #ff5454;
}

.card.buy > h3 {
  color: #4ca497;
}
</style>
