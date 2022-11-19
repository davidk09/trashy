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
      <listing-component price="10" :buy="true" qty="100" />
      <listing-component price="100" :buy="false" qty="10" />
    </div>
    <h2 style="margin-top: 1rem">Past transactions:</h2>
    <div>
      <transaction-component
        price="100"
        :buy="false"
        qty="10"
        date="19.11.2022"
      />
      <transaction-component
        price="10"
        :buy="true"
        qty="100"
        date="11.09.2022"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import PageTitle from "@/components/PageTitle";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import ListingComponent from "@/components/ListingComponent.vue";
import TransactionComponent from "@/components/TransactionComponent.vue";
</script>
