import { ExchangeRequests } from "@/scripts/exchange";

class RequestCollection {
  apiUrl: string;
  exchange: ExchangeRequests;

  constructor(apiUrl: string) {
    this.apiUrl = apiUrl;
    this.exchange = new ExchangeRequests(this, "orders/");
  }
}

export default RequestCollection;
