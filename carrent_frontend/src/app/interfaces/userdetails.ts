// src/app/interfaces/user-details.ts

import { User } from './auth';  // Assuming you have a User interface defined
import { License } from './license';  // Assuming you have a License interface defined

export interface UserDetails {
  userId: number;
  user?: User;        // Optional if you do not always fetch the user info
  driverLicense?: License;  // Optional based on your application's logic
  name: string;
  surname: string;
  location: string;
  image: string;
}
