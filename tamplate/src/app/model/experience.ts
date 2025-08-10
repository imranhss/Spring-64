export interface Experience {
  id?: number;
  company: string;
  position: string;
  fromDate: string; // yyyy-MM-dd
  toDate?: string;  // yyyy-MM-dd
  description?: string;
}