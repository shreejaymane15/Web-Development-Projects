// components/RouteProtection.js
"use client";
import React from 'react';
import { useRouter } from 'next/navigation';
import { useCookies } from 'next-client-cookies';


const ProtectedRoute = ({ children }) => {
    const router = useRouter();
    const cookies = useCookies();
    React.useEffect(() => {
        // Check if the user is authenticated, redirect to login if not.
        if (!cookies.get("token")) {
            router.push('/login'); // Redirect to the login page.
        }
    }, [cookies]);
    return <>{children}</>;
};

export default ProtectedRoute;