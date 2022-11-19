<template>
  <div
    v-show="show"
    style="
      position: fixed;
      top: 0;
      bottom: 0;
      left: 0;
      right: 0;
      z-index: 2000;
      background-color: black;
    "
  >
    <video
      autoplay
      style="width: 100%; height: 100%; object-fit: cover"
      ref="videoPreview"
    ></video>
    <button
      class="button"
      style="
        position: absolute;
        bottom: 0;
        left: 50%;
        transform: translate(-50%, -50%);
      "
      @click="takePicture"
    >
      Take Trashy
    </button>
    <button
      class="button"
      style="
        position: absolute;
        top: 0;
        left: 0;
        transform: translate(50%, 50%);
        line-height: 1;
      "
      @click="close"
    >
      <font-awesome-icon icon="xmark" />
    </button>
    <canvas ref="canvas"></canvas>
    <a ref="tempLink"></a>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

const show = ref(false);
const videoPreview = ref<HTMLVideoElement>();
const canvas = ref<HTMLCanvasElement>();
const tempLink = ref<HTMLAnchorElement>();

let mediaStream: MediaStream;

function open() {
  if (!navigator.mediaDevices.getUserMedia) return;
  setupVideoPreview();
  show.value = true;
}

function close() {
  if (!show.value || !videoPreview.value) return;
  show.value = false;
  videoPreview.value.srcObject = null;
  mediaStream.getTracks().forEach((track) => track.stop());
}

function setupVideoPreview() {
  navigator.mediaDevices
    .getUserMedia({ video: true })
    .then((stream) => {
      if (!videoPreview.value) return;
      videoPreview.value.srcObject = stream;
      mediaStream = stream;
    })
    .catch(console.log);
}

function takePicture() {
  if (!videoPreview.value || !canvas.value || !tempLink.value) return;

  const width = videoPreview.value.videoWidth;
  const height = videoPreview.value.videoHeight;
  const ctx = canvas.value.getContext("2d");

  if (!ctx) return;

  canvas.value.width = width;
  canvas.value.height = height;
  ctx.drawImage(videoPreview.value, 0, 0, width, height);

  let imgURL = canvas.value.toDataURL();
  // window.open(imgURL);
}

defineExpose({ open: open, close: close });
</script>
