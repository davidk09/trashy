<template>
  <div class="container">
    <page-title
      title="Marketplace"
      subtitle="buy and sell trashcans"
      icon="arrow-left"
    />
    <div class="gapped-group">
      <button class="button" @click="orderPopup?.open(true)">
        Buy Trashcans
      </button>
      <button class="button" @click="orderPopup?.open(false)">
        Sell Trashcans
      </button>
    </div>
    <h2 style="margin-top: 3rem">Your listings:</h2>
    <div>
      <listing-component
        v-for="order of listings"
        :key="order.id"
        :price="order.price"
        :buy="order.type === OrderType.BUY"
        :qty="order.quantity"
        @cancel="confirmPopup?.open(order.id)"
      />
    </div>
    <h2 style="margin-top: 1rem">Past transactions:</h2>
    <div>
      <transaction-component
        :price="100"
        :buy="false"
        :qty="10"
        date="19.11.2022"
      />
      <transaction-component
        :price="10"
        :buy="true"
        :qty="100"
        date="11.09.2022"
      />
    </div>
  </div>
  <order-popup ref="orderPopup" @submit="fetchOrders" />
  <confirm-popup @confirm="deleteOrder" ref="confirmPopup" />
</template>

<script setup lang="ts">
import PageTitle from "@/components/PageTitle";
import ListingComponent from "@/components/ListingComponent.vue";
import TransactionComponent from "@/components/TransactionComponent.vue";
import OrderPopup from "@/components/OrderPopup.vue";
import { inject, onMounted, ref } from "vue";
import ConfirmPopup from "@/components/ConfirmPopup.vue";
import RequestCollection from "@/scripts/requests";
import { Order, OrderType } from "@/scripts/exchange";

const listings = ref<Order[]>([] as Order[]);
const orderPopup = ref<typeof OrderPopup>();
const confirmPopup = ref<typeof ConfirmPopup>();
const reqUtil = inject<RequestCollection>("reqUtil");

function deleteOrder(id: number) {
  if (!reqUtil) return;
  confirmPopup.value?.close();
  reqUtil.exchange.deleteOrder(id).then(fetchOrders).catch(console.log);
}

function fetchOrders() {
  if (!reqUtil) return;
  reqUtil.exchange.getOrders(1).then((orders) => {
    listings.value = orders;
  });
}

onMounted(() => {
  fetchOrders();
});
</script>
