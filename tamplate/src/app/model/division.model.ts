import { Country } from "./country.model";

export interface Division {
    id: number;
    name?: string;   // make optional to fix the error
    country?: Country;
    districts?: number[];
}
