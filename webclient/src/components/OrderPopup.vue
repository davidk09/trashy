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
          <select v-model="usage" class="text-input">
            <option :value="CanType.HIGH">High usage</option>
            <option :value="CanType.MEDIUM">Medium usage</option>
            <option :value="CanType.LOW" selected>Low usage</option>
          </select>
        </div>

        <label>Quantity</label>
        <div class="inline-group mb-2">
          <span class="inline-group-label">
            <font-awesome-icon icon="arrow-trend-up" />
          </span>
          <input
            v-model="quantity"
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
          <input
            v-model="price"
            type="number"
            class="text-input"
            placeholder="Price"
            min="1"
          />
        </div>
        <div class="gapped-group">
          <button class="button" @click="submit">Create Offer</button>
          <button class="button accent" @click="close()">Cancel</button>
        </div>
      </div>
    </div>
    <div class="backdrop blur" :class="{ show }"></div>
  </div>
</template>

<script setup lang="ts">
import { inject, ref } from "vue";
import { CanType, Order, OrderType } from "@/scripts/exchange";
import RequestCollection from "@/scripts/requests";

const usage = ref(CanType.LOW);
const quantity = ref(1);
const price = ref(1);

const show = ref(false);
const buy = ref(false);

const reqUtil = inject<RequestCollection>("reqUtil");

defineExpose({ open: open, close: close, isShowing: show, buyMode: buy });
const emit = defineEmits(["submit"]);

function open(pBuy: boolean) {
  buy.value = pBuy;
  show.value = true;
}

function close() {
  show.value = false;
}

function submit() {
  if (!reqUtil) return;
  if (quantity.value < 1) quantity.value = 1;
  if (price.value < 1) price.value = 1;

  reqUtil.exchange
    .addOrder(1, {
      id: 0,
      type: buy.value ? OrderType.BUY : OrderType.SELL,
      canType: usage.value,
      price: price.value,
      quantity: quantity.value,
    } as Order)
    .then(() => {
      close();
      emit("submit");
    });
}
</script>
