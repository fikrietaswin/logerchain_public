"use client";

import {useEffect, useState} from "react";
import {useParams} from "next/navigation";
import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card";
import {Button} from "@/components/ui/button";
import Link from "next/link";
import Loading from "@/components/loading";
import ProtectedRoute from "@/components/protectedroute";
import api from "@/utils/baseApi";

/**
 * Represents the detailed information of a shipment.
 * @property {number} id - The ID of the shipment.
 * @property {string} sku - The SKU of the shipment.
 * @property {string} name - The name of the product.
 * @property {string} description - The description of the shipment.
 * @property {string} origin - The origin of the shipment.
 * @property {string} destination - The destination of the shipment.
 * @property {string} deliveryDate - The expected delivery date.
 * @property {number} units - The number of units in the shipment.
 * @property {number} weight - The weight of the shipment.
 * @property {string} currentState - The current state of the shipment.
 * @property {string} currentOwner - The current owner of the shipment.
 */
interface Shipment {
    id: number;
    sku: string;
    name: string;
    description: string;
    origin: string;
    destination: string;
    deliveryDate: string;
    units: number;
    weight: number;
    currentState: string;
    currentOwner: string;
}

/**
 * The shipment detail page.
 * <p>
 * This component displays the detailed information of a specific shipment.
 * </p>
 *
 * @returns {JSX.Element} The rendered shipment detail page.
 */
export default function ShipmentDetail() {
    const {id} = useParams();
    const [shipment, setShipment] = useState<Shipment | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        const fetchShipment = async () => {
            setLoading(true);
            const token = localStorage.getItem("authToken");
            try {
                const response = await fetch(`${api.baseURL}/api/shipment/${id}`, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${token}`
                    }
                });
                if (!response.ok) {
                    const errorMessage = await response.text();
                    setError(errorMessage);
                    setLoading(false);
                    return;
                }
                const data: Shipment = await response.json();
                setShipment(data);
            } catch (err) {
                console.error(err);
                setError("An error occurred while fetching shipment.");
            } finally {
                setLoading(false);
            }
        };
        fetchShipment();
    }, [id]);

    if (loading) {
        return <Loading/>;
    }

    if (error) {
        return (
            <Card>
                <CardHeader>
                    <CardTitle>Error</CardTitle>
                </CardHeader>
                <CardContent>
                    <p className="text-red-500">{error}</p>
                    <Link href="/shipments">
                        <Button className="mt-4">Back to Shipments</Button>
                    </Link>
                </CardContent>
            </Card>
        );
    }

    if (!shipment) {
        return null;
    }

    return (
        <ProtectedRoute>
            <Card>
                <CardHeader>
                    <CardTitle>Shipment Details</CardTitle>
                </CardHeader>
                <CardContent>
                    <p><strong>Name:</strong> {shipment.name}</p>
                    <p><strong>SKU:</strong> {shipment.sku}</p>
                    <p><strong>Description:</strong> {shipment.description}</p>
                    <p><strong>Origin:</strong> {shipment.origin}</p>
                    <p><strong>Destination:</strong> {shipment.destination}</p>
                    <p><strong>Delivery Date:</strong> {shipment.deliveryDate}</p>
                    <p><strong>Units:</strong> {shipment.units}</p>
                    <p><strong>Weight:</strong> {shipment.weight} kg</p>
                    <p><strong>Current State:</strong> {shipment.currentState}</p>
                    <p><strong>Current Owner:</strong> {shipment.currentOwner}</p>
                    <Link href="/shipments">
                        <Button className="mt-4">Back to Shipments</Button>
                    </Link>
                </CardContent>
            </Card>
        </ProtectedRoute>
    );
}
