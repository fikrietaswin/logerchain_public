import {type ClassValue, clsx} from "clsx"
import {twMerge} from "tailwind-merge"

/**
 * A utility function to merge Tailwind CSS classes.
 *
 * @param {...ClassValue[]} inputs - The class values to merge.
 * @returns {string} The merged class string.
 */
export function cn(...inputs: ClassValue[]) {
    return twMerge(clsx(inputs))
}
