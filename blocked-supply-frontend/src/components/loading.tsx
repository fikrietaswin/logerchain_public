import React from 'react';
import Image from 'next/image';
import '@/styles/globals.css';

/**
 * A component that displays a loading indicator.
 * <p>
 * This component shows a loading GIF centered on the screen with an overlay.
 * </p>
 *
 * @returns {JSX.Element} The rendered loading component.
 */
const Loading: React.FC = () => (
    <div className="loading-overlay">
        <Image
            src="/load-block.gif"
            alt="Loading..."
            width={100}
            height={100}
            className="loading-gif"
            unoptimized={true}
        />
    </div>
);

export default Loading;