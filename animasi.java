 <script>
        // Initialize cosmic background
        function createCosmicBackground() {
            const bg = document.getElementById('cosmicBg');
            
            // Create stars
            for (let i = 0; i < 200; i++) {
                const star = document.createElement('div');
                star.className = 'star';
                star.style.width = Math.random() * 3 + 1 + 'px';
                star.style.height = star.style.width;
                star.style.left = Math.random() * 100 + '%';
                star.style.top = Math.random() * 100 + '%';
                star.style.opacity = Math.random() * 0.5 + 0.2;
                star.style.setProperty('--duration', Math.random() * 3 + 2 + 's');
                bg.appendChild(star);
            }
            
            // Create floating elements
            const shapes = ['✦', '✧', '❀', '✿', '♡', '★', '☆'];
            for (let i = 0; i < 30; i++) {
                const element = document.createElement('div');
                element.className = 'floating-element';
                element.innerHTML = shapes[Math.floor(Math.random() * shapes.length)];
                element.style.fontSize = Math.random() * 20 + 10 + 'px';
                element.style.color = `rgba(255, 64, 129, ${Math.random() * 0.3 + 0.1})`;
                element.style.left = Math.random() * 100 + '%';
                element.style.top = Math.random() * 100 + '%';
                element.style.animationDuration = Math.random() * 30 + 20 + 's';
                element.style.animationDelay = Math.random() * 5 + 's';
                document.body.appendChild(element);
            }
        }

        // Loading screen
        window.addEventListener('load', function() {
            const loadingScreen = document.getElementById('loadingScreen');
            const ageNumber = document.getElementById('ageNumber');
            
            // Create cosmic background
            createCosmicBackground();
            
            // Age counter animation
            let count = 0;
            const counter = setInterval(() => {
                if (count < 21) {
                    count++;
                    ageNumber.textContent = count;
                    
                    // Add sparkle effect when reaching 21
                    if (count === 21) {
                        createSparkleBurst();
                    }
                } else {
                    clearInterval(counter);
                }
            }, 80);
            
            // Hide loading screen
            setTimeout(() => {
                loadingScreen.style.opacity = '0';
                setTimeout(() => {
                    loadingScreen.style.display = 'none';
                    initializeMagic();
                }, 1500);
            }, 3000);
        });

        // Custom cursor
        const cursor = document.getElementById('cursor');
        const cursorFollower = document.getElementById('cursorFollower');
        
        document.addEventListener('mousemove', (e) => {
            cursor.style.left = e.clientX + 'px';
            cursor.style.top = e.clientY + 'px';
            
            cursorFollower.style.left = e.clientX + 'px';
            cursorFollower.style.top = e.clientY + 'px';
        });

        // Initialize magic effects
        function initializeMagic() {
            // Photo hover effect
            const photo = document.getElementById('mainPhoto');
            const frame = document.querySelector('.photo-magic-frame');
            
            frame.addEventListener('mouseenter', () => {
                createParticleBurst(frame.getBoundingClientRect());
            });
            
            // Magic button
            const magicButton = document.getElementById('magicButton');
            magicButton.addEventListener('click', () => {
                createLoveExplosion();
                magicButton.innerHTML = '<i class="fas fa-heart"></i> Keajaiban Terkirim!';
                magicButton.style.background = 'var(--gradient-rose)';
                
                setTimeout(() => {
                    magicButton.innerHTML = '<i class="fas fa-wand-magic-sparkles"></i> Kirim Keajaiban Cinta';
                    magicButton.style.background = 'var(--gradient)';
                }, 2000);
            });
            
            // Text hover effects
            const magicWords = document.querySelectorAll('.magic-word');
            magicWords.forEach(word => {
                word.addEventListener('mouseenter', () => {
                    createTextSparkle(word);
                });
            });
            
            // Scroll animations
            const observerOptions = {
                threshold: 0.1,
                rootMargin: '0px 0px -100px 0px'
            };
            
            const observer = new IntersectionObserver((entries) => {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        entry.target.style.opacity = '1';
                        entry.target.style.transform = 'translateY(0)';
                        
                        if (entry.target.classList.contains('message-orb')) {
                            createOrbGlow(entry.target);
                        }
                    }
                });
            }, observerOptions);
            
            // Observe all animated elements
            document.querySelectorAll('.message-orb, .sma-section, .galaxy-section').forEach(el => {
                el.style.opacity = '0';
                el.style.transform = 'translateY(50px)';
                el.style.transition = 'opacity 0.8s ease-out, transform 0.8s ease-out';
                observer.observe(el);
            });
        }

        // Particle burst effect
        function createParticleBurst(rect) {
            const centerX = rect.left + rect.width / 2;
            const centerY = rect.top + rect.height / 2;
            
            for (let i = 0; i < 30; i++) {
                const particle = document.createElement('div');
                particle.style.position = 'fixed';
                particle.style.width = '10px';
                particle.style.height = '10px';
                particle.style.background = `hsl(${Math.random() * 60 + 300}, 100%, 65%)`;
                particle.style.borderRadius = '50%';
                particle.style.left = centerX + 'px';
                particle.style.top = centerY + 'px';
                particle.style.zIndex = '9999';
                particle.style.pointerEvents = 'none';
                particle.style.boxShadow = '0 0 10px currentColor';
                
                document.body.appendChild(particle);
                
                const angle = Math.random() * Math.PI * 2;
                const distance = Math.random() * 100 + 50;
                const duration = Math.random() * 1000 + 500;
                
                const animation = particle.animate([
                    {
                        transform: 'translate(0, 0) scale(1)',
                        opacity: 1
                    },
                    {
                        transform: `translate(${Math.cos(angle) * distance}px, ${Math.sin(angle) * distance}px) scale(0)`,
                        opacity: 0
                    }
                ], {
                    duration: duration,
                    easing: 'cubic-bezier(0.215, 0.610, 0.355, 1)'
                });
                
                animation.onfinish = () => {
                    particle.remove();
                };
            }
        }

        // Sparkle burst effect
        function createSparkleBurst() {
            const centerX = window.innerWidth / 2;
            const centerY = window.innerHeight / 2;
            
            for (let i = 0; i < 100; i++) {
                const sparkle = document.createElement('div');
                sparkle.style.position = 'fixed';
                sparkle.style.fontSize = '2rem';
                sparkle.style.opacity = '0';
                sparkle.innerHTML = '✨';
                sparkle.style.left = centerX + 'px';
                sparkle.style.top = centerY + 'px';
                sparkle.style.zIndex = '9999';
                sparkle.style.pointerEvents = 'none';
                sparkle.style.transform = 'translate(-50%, -50%)';
                
                document.body.appendChild(sparkle);
                
                const angle = Math.random() * Math.PI * 2;
                const distance = Math.random() * 300 + 100;
                const duration = Math.random() * 2000 + 1000;
                
                const animation = sparkle.animate([
                    {
                        transform: 'translate(-50%, -50%) scale(0) rotate(0deg)',
                        opacity: 0
                    },
                    {
                        transform: `translate(${Math.cos(angle) * distance - 50}px, ${Math.sin(angle) * distance - 50}px) scale(1) rotate(360deg)`,
                        opacity: 1,
                        offset: 0.3
                    },
                    {
                        transform: `translate(${Math.cos(angle) * distance - 50}px, ${Math.sin(angle) * distance - 50}px) scale(0) rotate(720deg)`,
                        opacity: 0
                    }
                ], {
                    duration: duration,
                    easing: 'cubic-bezier(0.215, 0.610, 0.355, 1)'
                });
                
                animation.onfinish = () => {
                    sparkle.remove();
                };
            }
        }

        // Love explosion effect
        function createLoveExplosion() {
            const hearts = ['❤️', '💖', '💕', '💗', '💓', '💞', '💝'];
            
            for (let i = 0; i < 50; i++) {
                setTimeout(() => {
                    const heart = document.createElement('div');
                    heart.style.position = 'fixed';
                    heart.style.fontSize = Math.random() * 30 + 20 + 'px';
                    heart.innerHTML = hearts[Math.floor(Math.random() * hearts.length)];
                    heart.style.left = Math.random() * 100 + '%';
                    heart.style.top = '100%';
                    heart.style.zIndex = '9999';
                    heart.style.pointerEvents = 'none';
                    heart.style.opacity = '0';
                    
                    document.body.appendChild(heart);
                    
                    const animation = heart.animate([
                        {
                            transform: 'translateY(0) scale(0)',
                            opacity: 0
                        },
                        {
                            transform: `translateY(${Math.random() * -100 - 50}px) scale(1)`,
                            opacity: 1,
                            offset: 0.3
                        },
                        {
                            transform: `translateY(${Math.random() * -600 - 200}px) scale(0.5)`,
                            opacity: 0
                        }
                    ], {
                        duration: Math.random() * 3000 + 2000,
                        easing: 'cubic-bezier(0.215, 0.610, 0.355, 1)'
                    });
                    
                    animation.onfinish = () => {
                        heart.remove();
                    };
                }, i * 50);
            }
        }

        // Text sparkle effect
        function createTextSparkle(element) {
            const rect = element.getBoundingClientRect();
            
            for (let i = 0; i < 10; i++) {
                const spark = document.createElement('div');
                spark.style.position = 'fixed';
                spark.style.width = '4px';
                spark.style.height = '4px';
                spark.style.background = 'white';
                spark.style.borderRadius = '50%';
                spark.style.left = rect.left + Math.random() * rect.width + 'px';
                spark.style.top = rect.top + Math.random() * rect.height + 'px';
                spark.style.zIndex = '9999';
                spark.style.pointerEvents = 'none';
                spark.style.boxShadow = '0 0 10px white';
                
                document.body.appendChild(spark);
                
                const animation = spark.animate([
                    {
                        transform: 'scale(1)',
                        opacity: 1
                    },
                    {
                        transform: 'scale(0)',
                        opacity: 0
                    }
                ], {
                    duration: 500,
                    easing: 'ease-out'
                });
                
                animation.onfinish = () => {
                    spark.remove();
                };
            }
        }

        // Orb glow effect
        function createOrbGlow(orb) {
            const glow = document.createElement('div');
            glow.style.position = 'absolute';
            glow.style.top = '0';
            glow.style.left = '0';
            glow.style.width = '100%';
            glow.style.height = '100%';
            glow.style.background = 'radial-gradient(circle, rgba(255,64,129,0.2) 0%, transparent 70%)';
            glow.style.borderRadius = '25px';
            glow.style.opacity = '0';
            glow.style.zIndex = '-1';
            
            orb.appendChild(glow);
            
            const animation = glow.animate([
                { opacity: 0 },
                { opacity: 1 },
                { opacity: 0 }
            ], {
                duration: 2000,
                easing: 'ease-in-out'
            });
            
            animation.onfinish = () => {
                glow.remove();
            };
        }

        // Interactive background on click
        document.addEventListener('click', (e) => {
            createRipple(e.clientX, e.clientY);
        });

        // Ripple effect
        function createRipple(x, y) {
            const ripple = document.createElement('div');
            ripple.style.position = 'fixed';
            ripple.style.left = x + 'px';
            ripple.style.top = y + 'px';
            ripple.style.width = '0';
            ripple.style.height = '0';
            ripple.style.borderRadius = '50%';
            ripple.style.background = 'radial-gradient(circle, rgba(255,64,129,0.3) 0%, transparent 70%)';
            ripple.style.transform = 'translate(-50%, -50%)';
            ripple.style.zIndex = '1';
            ripple.style.pointerEvents = 'none';
            
            document.body.appendChild(ripple);
            
            const animation = ripple.animate([
                {
                    width: '0',
                    height: '0',
                    opacity: 1
                },
                {
                    width: '400px',
                    height: '400px',
                    opacity: 0
                }
            ], {
                duration: 1500,
                easing: 'ease-out'
            });
            
            animation.onfinish = () => {
                ripple.remove();
            };
        }

        // Console birthday message
        console.log('%c✨✨✨ HAPPY 21ST BIRTHDAY LIA SALMA ✨✨✨', 'background: linear-gradient(90deg, #ff4081, #9c27b0); color: white; font-size: 24px; padding: 20px; border-radius: 10px;');
        console.log('%cMay your 21st year be filled with magic, love, and endless possibilities!', 'color: #ffd700; font-size: 16px;');
    </script>