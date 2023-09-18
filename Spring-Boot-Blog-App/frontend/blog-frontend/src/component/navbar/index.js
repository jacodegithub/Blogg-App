import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import {Collapse, DropdownItem, DropdownMenu, DropdownToggle, Nav, NavItem, NavLink, Navbar, NavbarBrand, NavbarText, NavbarToggler, UncontrolledDropdown} from 'reactstrap'

function BlogNavbar(args) {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  return (
    <div>
        <Navbar color='dark' dark expand='md' fixed='' {...args}>
            <NavbarBrand href="/">blog'in</NavbarBrand>
            <NavbarToggler onClick={toggle} />
            <Collapse isOpen={isOpen} navbar>
            <Nav className="me-auto" navbar>
                <NavItem>
                <NavLink href="/">Home</NavLink>
                </NavItem>
                <NavItem>
                <NavLink href="https://github.com/reactstrap/reactstrap">
                    My Posts
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
            <NavbarText className='mx-2'>
                <Link to={`/login`}>Login</Link>
            </NavbarText>
            <NavbarText className='mx-2'>
                <Link to={`/register`}>SignUp</Link>
            </NavbarText>
            </Collapse>
        </Navbar>      
    </div>
  )
}

export default BlogNavbar;