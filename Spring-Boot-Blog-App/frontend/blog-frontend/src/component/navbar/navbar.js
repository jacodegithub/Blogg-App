import React, { useState } from 'react';
import { NavLink as ReactLink, useNavigate } from 'react-router-dom';
import {Collapse, DropdownItem, DropdownMenu, DropdownToggle, Nav, NavItem, NavLink, Navbar, NavbarBrand, NavbarText, NavbarToggler, UncontrolledDropdown} from 'reactstrap'
import { getCurrentUserDetails } from '../../service/sessionService';

function BlogNavbar(args) {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  const currentUser = getCurrentUserDetails();
  console.log('user ->', currentUser)

  return (
    <div>
        <Navbar color='dark' dark expand='md' fixed='' {...args}>
            <NavbarBrand href="/">blog'in</NavbarBrand>
            <NavbarToggler onClick={toggle} />
            <Collapse isOpen={isOpen} navbar>
            <Nav className="me-auto" navbar>
                <NavItem>
                <NavLink className="pointer" tag={ReactLink} to={'/'}>Home</NavLink>
                </NavItem>
                <NavItem>
                <NavLink className='pointer' tag={ReactLink} to={'/'}>
                    My posts
                </NavLink>
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
                <NavItem className='mx-2'>
                    <NavLink className='pointer' tag={ReactLink} to={`/login`}>Login</NavLink>
                </NavItem>
                <NavItem className='mx-2'>
                    <NavLink className='pointer' tag={ReactLink} to={`/register`}>SignUp</NavLink>
                </NavItem>
            </Nav>
            </Collapse>
        </Navbar>      
    </div>
  )
}

export default BlogNavbar;