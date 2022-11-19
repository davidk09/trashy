<template>
  <div>
    <div class="dialog" :class="{ show }" style="width: 95%; max-width: 800px">
      <div class="card" :class="buy ? 'buy' : 'sell'">
        <h3 class="mb-3">{{ buy ? "Buy" : "Sell" }} Trashcans</h3>

        <label>Trashcan usage:</label>
        <div class="inline-group mb-2">
          <span class="inline-group-label">
            <font-awesome-icon icon="money-bill" />
          </span>
          <select class="text-input">
            <option selected>Select usage...</option>
            <option>High usage</option>
            <option>Medium usage</option>
            <option>Low usage</option>
          </select>
        </div>

        <label>Quantity</label>
        <div class="inline-group mb-2">
          <span class="inline-group-label">
            <font-awesome-icon icon="arrow-trend-up" />
          </span>
          <input
            type="number"
            class="text-input"
            placeholder="Quantity"
            min="1"
          />
        </div>

        <span>Price</span>
        <div class="inline-group mb-2">
          <span class="inline-group-label">
            <font-awesome-icon icon="recycle" />
          </span>
          <input type="number" class="text-input" placeholder="Price" min="1" />
        </div>
        <div class="gapped-group">
          <button class="button">Create Offer</button>
          <button class="button accent" @click="close()">Cancel</button>
        </div>
      </div>
    </div>
    <div class="backdrop blur" :class="{ show }"></div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const show = ref(false);
const buy = ref(false);

function open(pBuy: boolean) {
  buy.value = pBuy;
  show.value = true;
}

function close() {
  show.value = false;
}

defineExpose({ open: open, close: close, isShowing: show, buyMode: buy });
defineEmits(["submit"]);
</script>
