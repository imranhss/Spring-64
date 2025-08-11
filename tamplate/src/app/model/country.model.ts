export interface Country {
  id?: number;         // optional for new countries before saving
  name: string;
  divisions?: number[]; // list of division IDs (optional)
}
