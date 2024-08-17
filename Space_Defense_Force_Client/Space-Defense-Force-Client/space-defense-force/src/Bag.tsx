import { Box, Card, Divider, ImageList, ImageListItem, Modal, Pagination, Paper, Tooltip, Typography, styled } from "@mui/material";
import { useState } from "react";

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  textAlign: 'center',
  color: theme.palette.text.secondary,
  height: 60,
  lineHeight: '60px',
}));

export default function Bag() {
  const [page, setPage] = useState(1);
  const [itemData, setItemData] = useState([
    {name: "Uno", health: "1000", attack: "100", img: "https://cdn.pixabay.com/photo/2012/04/23/15/20/one-38484_1280.png"},
    {name: "Duos", health: "700", attack: "300",img: "https://i.cbc.ca/1.6979706.1695828636!/fileImage/httpImage/image.jpg_gen/derivatives/16x9_780/1890767731.jpg"},
    {name: "Tres", health: "500", attack: "500",img: "https://www.shutterstock.com/shutterstock/photos/2179827025/display_1500/stock-photo-fire-alphabet-number-made-of-fire-flames-with-red-smoke-behind-hot-metal-font-in-flames-2179827025.jpg"},
    {name: "Quatro", health: "900", attack: "100",img: "data:image/png;base64,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"},
    {name: "Cinco", health: "400", attack: "600",img: "https://static.wikia.nocookie.net/knd/images/1/10/Numbuh_5.jpg/revision/latest?cb=20221225205341"},
    {name: "Seis", health: "200", attack: "800",img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRApTnfoEbsfAA6WGWS-ufPdnKIomORlpwscw&s"},
    {name: "Seite", health: "600", attack: "400",img: "https://parade.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTk5NjgwNzkyNDc2MDAxOTIw/life-path-number-7.jpg"},
    {name: "Ocho", health: "300", attack: "700",img: "https://cdn-icons-png.flaticon.com/512/6335/6335618.png"},
    {name: "Nueve", health: "750", attack: "250",img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKHM_zA04I14uJVRh8__4WANFd1unNIYM5CA&s"},
  ])
  const [open, setOpen] = useState(false);
  const [hoveredItem, setHoveredItem] = useState<{ name: string, health: string, attack: string, img: string } | null>(null);

  const handleMouseEnter = (item: { name: string, health: string, attack: string, img: string }) => {
    setHoveredItem(item);
    setOpen(true);
  };
  
  const handleMouseLeave = () => {
    setOpen(false);
    setHoveredItem(null); // Clear the hovered item
  };  

  const handlePageTransition = (event: React.ChangeEvent<unknown>, value: number) => {
    setPage(value)
  }

  return(
    <Card 
    variant="outlined" 
    sx={{
      backgroundColor: "white", 
      color: 'black', 
      height: '100%'
    }}>
      {/* Top-aligned content */}
      <div style={{ alignSelf: 'start', justifySelf: 'center', width: '100%', display: "grid", height: "10%", border: "solid red" }}>
        <Typography
          sx={{
            justifySelf: "center",
            alignSelf: "center"
          }}
        >
          Page: {page}
        </Typography>
        <Divider/>
      </div>

      {/* Centered content */}
      <div style={{ alignSelf: 'center', justifySelf: 'center', width: '100%', height: "80%", display: "grid", border: "solid yellow" }}>
        <ImageList sx={{ width: "80%", height: "80%", justifySelf: "center", alignSelf: "center" }} cols={3} rowHeight={125} >
          {itemData.map((item) => (
            <Tooltip title="Hover to open modal" placement="top">
              <ImageListItem 
                key={item.img} 
                onMouseEnter={() => handleMouseEnter(item)}
                onMouseLeave={handleMouseLeave}
                sx={{
                  justifySelf: "center",
                  alignSelf: "center",
                  height: "100%",
                  width: "100%"
              }}>
                <Item key={item.img} elevation={6} style={{ display: "grid", height: "95%", width: "100%"}}>
                <img
                  srcSet={`${item.img}?w=164&h=164&fit=crop&auto=format&dpr=2 2x`}
                  src={`${item.img}?w=164&h=164&fit=crop&auto=format`}
                  //alt={item.title}
                  loading="lazy"
                  style={{ width: "100%", height: "100%", overflow: "hidden"}}
                />
                </Item>
              </ImageListItem>
            </Tooltip>
          ))}
        </ImageList>
      </div>

      {/* Bottom-aligned content */}
      <div style={{ alignSelf: 'end', justifySelf: 'center', width: '100%', height: "10%", display: "grid", border: 'solid green'}}>
      <Divider/>
        <Pagination 
          count={5} 
          page={page}
          sx={{
            justifySelf: "center",
            alignSelf: "center"
          }} 
          onChange={handlePageTransition} />
        <Modal
            open={open}
            onClose={handleMouseLeave}
            aria-labelledby="hover-modal-title"
            aria-describedby="hover-modal-description"
            closeAfterTransition
        >
          <Box
            sx={{
              position: 'absolute',
              top: '25%',
              left: '50%',
              transform: 'translate(-50%, -50%)',
              width: 300,
              bgcolor: 'background.paper',
              border: '2px solid #000',
              boxShadow: 24,
              p: 4,
            }}
          >
            {hoveredItem?.name}
            <div>Health: {hoveredItem?.health}</div>
            <div>Attack: {hoveredItem?.attack}</div>
          </Box>
        </Modal>
      </div>
    </Card>
  )
}