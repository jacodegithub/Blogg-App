import React, { useEffect, useState } from 'react';
import { NavLink as ReactLink } from 'react-router-dom';
import {Collapse, DropdownItem, DropdownMenu, DropdownToggle, Nav, NavItem, NavLink, Navbar, NavbarBrand, NavbarToggler, UncontrolledDropdown} from 'reactstrap'
import { doLogout, getCurrentUserDetails, isLoggedIn } from '../../service/sessionService';

function BlogNavbar() {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  const [currentUser, setCurrentUser] = useState([]);
  
  const [login, setLogin] = useState(false)
    useEffect(() => {
        if(isLoggedIn()) {
            setCurrentUser(getCurrentUserDetails)
            setLogin(true)
        }
    }, [login])

  function handleLogout() {
    doLogout()
    setLogin(false)
  }

  const userId = currentUser.id;

  return (
    <div>
        <Navbar color='dark' dark expand='md' fixed='' >
            <NavbarBrand href="/">blog'in</NavbarBrand>
            <NavbarToggler onClick={toggle} />
            <Collapse isOpen={isOpen} navbar>
            <Nav className="me-auto" navbar>
                <NavItem>
                <NavLink className="pointer" tag={ReactLink} to={'/'}>Home</NavLink>
                </NavItem>
                <NavItem>
                    { 
                        login && (
                            <NavLink className='pointer' tag={ReactLink} to={`/user-posts/${userId}`}>
                                <span>My Posts</span>
                            </NavLink>
                        )
                    }
                </NavItem>
                <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav caret>
                    Options
                </DropdownToggle>
                <DropdownMenu end>
                    <DropdownItem>Option 1</DropdownItem>
                    <DropdownItem>Option 2</DropdownItem>
                    <DropdownItem divider />
                    <DropdownItem>Reset</DropdownItem>
                </DropdownMenu>
                </UncontrolledDropdown>
            </Nav>
            <Nav className='auto' navbar>
                {
                    !login && (    
                        <>

                        <NavItem className='mx-2'>
                            <NavLink className='pointer' tag={ReactLink} to={`/login`}>Login</NavLink>
                        </NavItem>
                        <NavItem className='mx-2'>
                            <NavLink className='pointer' tag={ReactLink} to={`/register`}>SignUp</NavLink>
                        </NavItem>
                        <NavItem className='mx-2'>
                           <NavLink className='pointer' href='/' ><span onClick={handleLogout}>Logout</span></NavLink>
                        </NavItem>
                        
                        </>
                    )
                }
            </Nav>
            </Collapse>
        </Navbar>      
    </div>
  )
}

export default BlogNavbar;