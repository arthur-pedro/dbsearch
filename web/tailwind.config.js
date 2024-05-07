/** @type {import('tailwindcss').Config} */
module.exports = {
  presets: [require("@spartan-ng/ui-core/hlm-tailwind-preset")],
  content: ["./src/**/*.{html,ts}", "./src/@core/components/**/*.{html,ts}"],
  darkMode: 'class',
  theme: {
    container: {
      center: true,
    },
    extend: {},
  },
  plugins: [],
};
