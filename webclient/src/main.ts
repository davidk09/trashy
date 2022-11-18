import { createApp } from "vue";
import App from "./App.vue";
import router from "@/router";

import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

import axios from "axios";
import VueAxios from "vue-axios";

library.add(fas, far, fab);

const app = createApp(App);
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(VueAxios, axios);
app.use(router);
app.mount("#app");
