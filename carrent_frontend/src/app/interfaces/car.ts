// src/app/models/car.model.ts

export interface Car {
  id?: number; // Optional because it won't be present when creating a new car
  fuel: string;
  transmission: string;
  consumption: string;
  seats: number;
  luggageCapacity: string;
  image: string;
  model: string;
  price: number;
}
