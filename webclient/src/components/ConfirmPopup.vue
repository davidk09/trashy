<template>
  <div>
    <div class="dialog" :class="{ show }" style="width: 95%; max-width: 800px">
      <div class="card sell">
        <h3>Are you sure?</h3>
        <p class="mt-2">{{ message }}</p>
        <div class="gapped-group mt-2">
          <button class="button" @click="close()">Cancel</button>
          <button class="button accent">Confirm</button>
        </div>
      </div>
    </div>
    <div class="backdrop blur" :class="{ show }"></div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const defaultMessage =
  "Do you really want to cancel your offer? This can not be undone";
const show = ref(false);
const message = ref(defaultMessage);

function open(pMessage = "") {
  if (pMessage != "") message.value = pMessage;
  else message.value = defaultMessage;
  show.value = true;
}

function close() {
  show.value = false;
}

defineExpose({ open: open, close: close });
defineEmits(["confirm"]);
</script>
