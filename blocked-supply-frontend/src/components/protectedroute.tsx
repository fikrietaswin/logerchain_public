"use client";

import {useEffect} from "react";
import {useRouter} from "next/navigation";
import {useAuth} from "@/context/AuthContext";

/**
 * A higher-order component that protects a route from unauthenticated access.
 * <p>
 * This component checks if a user is authenticated. If not, it redirects them to the
 * authentication page.
 * </p>
 *
 * @param {object} props - The properties for the component.
 * @param {React.ReactNode} props.children - The child elements to render if the user is authenticated.
 * @returns {JSX.Element | null} The rendered child elements or null.
 */
export default function ProtectedRoute({children}: {children: React.ReactNode}) {
    const router = useRouter();
    const {token} = useAuth();

    useEffect(() => {
        if (!token) {
            router.push("/auth");
        }
    }, [token, router]);

    if (!token) return null;

    return <>{children}</>;
}
