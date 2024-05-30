export interface User {
    id: number;        // Make sure ID is a number if it's numeric in your backend
    email: string;
    password?: string; // Make password optional in case you're not updating it
    roleId?: number;   // Make roleId optional to accommodate not changing the role
  }
  