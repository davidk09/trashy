import axios from "axios";

class RequestCollection {
  apiUrl: string;
  exchange: ExchangeRequests;

  constructor(apiUrl: string) {
    this.apiUrl = apiUrl;
    this.exchange = new ExchangeRequests(this, "exchange");
  }
}

class ExchangeRequests {
  collection: RequestCollection;
  subDir: string;

  constructor(collection: RequestCollection, subDir: string) {
    this.collection = collection;
    this.subDir = subDir;
  }

  public getOffers() {
    const path = this.collection.apiUrl + this.subDir + "get-offers";
    const request = axios.get(path);
    request.catch(console.log);
    return request;
  }

  public addOffer(qty: number, price: number, buy: boolean) {
    const path = this.collection.apiUrl + this.subDir + "add-offer";
    const request = axios.post(path, {
      qty: qty,
      price: price,
      buy: buy,
    });
    request.catch(console.log);
    return request;
  }
}
