import React from 'react';
import styled from "styled-components";

const socialIcons = [
    {
        icon: "fa-linkedin-in",
        color:"#0082ca",
        link:"https://www.linkedin.com/in/luigi-mastronardo-a16207280/"
    },
    {
        icon: "fa-google",
        color:"#dd4b39",
        link:"mailto:luigimastronardo00@gmail.com"
    },
    {
        icon: "fa-github",
        color:"#333333",
        link:"https://github.com/peaceAndCode/"
    }
]

const SocialIcon = ({icon, color, link}) =>{
    return(
        <a
            type="button"
            className="round-social-icons"
            href={link} style={{backgroundColor:color, borderColor:color,color:'#fff'}}
            target="_blank"
            rel="noopener noreferrer"
        >
            <i className={`fab ${icon}`} style={{width:"14px",height:"14px"}}></i>
        </a>
    )
}

const Footer = () => {
    return (
        <FooterContainer>
            <div className="social-icons-container">
                {
                    socialIcons.map(({icon,color,link},index) => (
                        <SocialIcon key={index} icon={icon} color={color} link={link}/>
                    ))
                }
            </div>
            <p>© {new Date().getFullYear()} Copyright</p>
        </FooterContainer>
    );
};

const FooterContainer = styled.footer`
    margin-top: 3rem;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;

    & .social-icons-container{
        display: flex;
        gap: 0.75rem;
    }
    
    & .social-icons-container .round-social-icons{
        border-radius: 50%;
        width: 30px;
        height: 30px;
        display: flex;
        justify-content: center;
        align-items: center;
        text-decoration: none;
    }
    
`

export default Footer;