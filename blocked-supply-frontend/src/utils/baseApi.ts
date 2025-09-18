/**
 * An object containing the base URL for the API.
 *
 * @property {string} baseURL - The base URL for the API.
 */
const api = {
    baseURL: process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8080",
};

export default api;
