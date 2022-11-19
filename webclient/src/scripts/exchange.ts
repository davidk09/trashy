import axios from "axios";
import RequestCollection from "@/scripts/requests";

class ExchangeRequests {
  collection: RequestCollection;
  subDir: string;

  constructor(collection: RequestCollection, subDir: string) {
    this.collection = collection;
    this.subDir = subDir;
  }

  public async getOrders(userId: number): Promise<Order[]> {
    const path = this.collection.apiUrl + this.subDir + `user/${userId}`;
    return new Promise((resolve, reject) => {
      axios.get(path).then(
        (response) => {
          resolve(response.data as Order[]);
        },
        (err) => {
          reject(err);
        }
      );
    });
  }

  public async addOrder(userId: number, order: Order): Promise<number> {
    const path = this.collection.apiUrl + this.subDir;
    const payload = { userId: userId, order: order };
    return new Promise((resolve, reject) => {
      axios.post(path, payload).then(
        (response) => {
          resolve(response.status);
        },
        (err) => {
          reject(err);
        }
      );
    });
  }

  public deleteOrder(order: Order): Promise<number> {
    const path = this.collection.apiUrl + this.subDir + `${order.id}`;
    return new Promise((resolve, reject) => {
      axios.delete(path).then(
        (response) => {
          resolve(response.status);
        },
        (err) => {
          reject(err);
        }
      );
    });
  }
}

type Order = {
  id: number;
  type: OrderType;
  price: number;
  quantity: number;
  canType: CanType;
};

enum OrderType {
  BUY,
  SELL,
}

enum CanType {
  HIGH,
  MEDIUM,
  LOW,
}

export { ExchangeRequests, Order, OrderType, CanType };
