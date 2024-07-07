import {createBrowserRouter} from "react-router-dom";
import {Home, Login, Register} from "./components";

export const router = createBrowserRouter([
    {
        path:"/",
        element:<Home/>,
    },
    {
        path:"/signin",
        element:<Login/>
    },
    {
        path:"/signup",
        element:<Register/>
    },

])