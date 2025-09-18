import {createContext, useContext, useEffect, useState} from "react";
import {useRouter} from "next/navigation";
import Loading from "@/components/loading";
import api from "@/utils/baseApi";

/**
 * Represents the authentication context type.
 * @property {string | null} token - The authentication token.
 * @property {(token: string) => void} login - The login function.
 * @property {() => void} logout - The logout function.
 */
interface AuthContextType {
    token: string | null;
    login: (token: string) => void;
    logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

/**
 * Provides authentication context to its children components.
 *
 * @param {object} props - The properties for the component.
 * @param {React.ReactNode} props.children - The child elements to render.
 * @returns {JSX.Element} The rendered authentication provider.
 */
export const AuthProvider = ({children}: { children: React.ReactNode }) => {
    const [token, setToken] = useState<string | null>(null);
    const [loading, setLoading] = useState(true);
    const router = useRouter();

    useEffect(() => {
        if (typeof window !== 'undefined') {
            const storedToken = localStorage.getItem("authToken");
            setToken(storedToken);
            setLoading(false);
        }
    }, []);

    const login = (newToken: string) => {
        localStorage.setItem("authToken", newToken);
        setToken(newToken);
        router.push("/");
    };

    const logout = async () => {
        const token = localStorage.getItem("authToken");
        await fetch(`${api.baseURL}/auth/logout`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
        });
        localStorage.removeItem("authToken");
        setToken(null);
        router.push("/");
    };

    if (loading) {
        return <Loading/>;
    }

    return (
        <AuthContext.Provider value={{token, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
};

/**
 * A hook to use the authentication context.
 *
 * @returns {AuthContextType} The authentication context.
 * @throws {Error} If used outside of an AuthProvider.
 */
export const useAuth = (): AuthContextType => {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error("useAuth must be used within an AuthProvider");
    }
    return context;
};
